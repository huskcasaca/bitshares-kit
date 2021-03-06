package graphene.protocol

import graphene.serializers.StaticVarSerializer
import graphene.serializers.TimePointSecSerializer
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = VestingPolicyInitializerSerializer::class)
sealed class VestingPolicyInitializer

@Serializable
data class LinearVestingPolicyInitializer(
    /** while vesting begins on begin_timestamp, none may be claimed before vesting_cliff_seconds have passed  */
    @SerialName("begin_timestamp") @Serializable(with = TimePointSecSerializer::class) val beginTimestamp: Instant,
    @SerialName("vesting_cliff_seconds") val vestingCliffSeconds: UInt32, // = 0
    @SerialName("vesting_duration_seconds") val vestingDurationSeconds: UInt32, // = 0
) : VestingPolicyInitializer()

@Serializable
data class CddVestingPolicyInitializer(
    /** while coindays may accrue over time, none may be claimed before the start_claim time  */
    @SerialName("start_claim") @Serializable(with = TimePointSecSerializer::class)  val startClaim: Instant,
    @SerialName("vesting_seconds") val vestingSeconds: UInt32, // = 0
) : VestingPolicyInitializer()

@Serializable
data class InstantVestingPolicyInitializer(
    @Transient val reserved: Unit = Unit
) : VestingPolicyInitializer()

object VestingPolicyInitializerSerializer : StaticVarSerializer<VestingPolicyInitializer>(
    listOf(
        LinearVestingPolicyInitializer::class,
        CddVestingPolicyInitializer::class,
        InstantVestingPolicyInitializer::class,
    )
)

