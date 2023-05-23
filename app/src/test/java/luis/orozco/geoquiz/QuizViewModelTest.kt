package luis.orozco.geoquiz

import androidx.lifecycle.SavedStateHandle
import org.junit.Assert.assertEquals
import org.junit.Test
const val IS_CHEATER_KEY = "IS_CHEATER_KEY"
class QuizViewModelTest {
    @Test
    fun proveeTextoPreguntaEsperada() {

        val savedStateHandle = SavedStateHandle()
        val quizViewModel = QuizViewModel(savedStateHandle)
        assertEquals(R.string.question_Jalisco, quizViewModel.currenQuestionText)
        quizViewModel.moveToNext()
        assertEquals(R.string.question_Ameca, quizViewModel.currenQuestionText)
    }
}




    
