package luis.orozco.geoquiz

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel


const val CURRENT_INDEX_KEY = "CURRENT:_INDEX_KEY"
const val IS_CHEATER_KEY = "IS_CHEATER_KEY"
class QuizViewModel(private val savedStateHandle:SavedStateHandle): ViewModel() {
    private val questionBank = listOf(
        Question(R.string.question_Jalisco,true),
        Question(R.string.question_Ameca,false),
        Question(R.string.question_Zacatecas,false),
        Question(R.string.question_Tlaxcala,false),
        Question(R.string.question_Villa,false),
        Question(R.string.question_Estipac,true)
    )
    var isCheater: Boolean
        get() = savedStateHandle.get(IS_CHEATER_KEY) ?: false
        set(value) = savedStateHandle.set(IS_CHEATER_KEY, value)

    private var currentIndex : Int
    get() = savedStateHandle.get(CURRENT_INDEX_KEY)?:0
    set(value)= savedStateHandle.set(CURRENT_INDEX_KEY,value)


    val currentQuestionAnswer: Boolean
        get()  = questionBank[currentIndex].answer
        val currenQuestionText:Int
        get() = questionBank[currentIndex].textResId
    fun moveToNext(){
        currentIndex = (currentIndex+1)% questionBank.size

    }
fun moveToPrev (){
    currentIndex = if (currentIndex == 0){
        questionBank.size-1
    }else
        (currentIndex - 1) % questionBank.size
}


    }

