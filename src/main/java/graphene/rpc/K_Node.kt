package graphene.rpc

data class K_Node(
    val name: String,
    val url: String,
    val username: String = "", // TODO: 2022/4/12
    val password: String = "", // TODO: 2022/4/12
)
