package graphene.chain

import graphene.protocol.CustomId
import graphene.protocol.CustomIdType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class K109_CustomObject(
    @SerialName("id")
    override val id: CustomId,
) : AbstractObject(), CustomIdType