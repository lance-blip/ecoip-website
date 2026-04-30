package za.co.quikle.lexio.domain.model

data class RightsCategory(
    val id: String,
    val name: String,           // e.g., "Your Rights at Work"
    val description: String,
    val iconName: String,       // Material icon name
    val topics: List<RightsTopic>
)
