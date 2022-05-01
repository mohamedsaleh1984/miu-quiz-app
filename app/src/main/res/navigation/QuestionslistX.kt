package navigation

data class QuestionslistX(
    val correctAnswer: Int,
    val id: String,
    val question: String,
    val questoinChoices: List<String>
)