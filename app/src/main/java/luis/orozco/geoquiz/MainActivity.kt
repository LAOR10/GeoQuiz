package luis.orozco.geoquiz

import android.app.Activity
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import luis.orozco.geoquiz.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val quizViewModel: QuizViewModel by viewModels()
    private val cheatLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){result ->
        //Hanflr the result
        if (result.resultCode == Activity.RESULT_OK) {
            quizViewModel.isCheater =
                result.data?.getBooleanExtra(EXTRA_ANSWER_SHOWN, false) ?: false
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.trueButton.setOnClickListener {view: View ->
        checkAnswer(true)

        }
            binding.falseButton.setOnClickListener {
        checkAnswer(false)
            }

            binding.nextButton.setOnClickListener {
              quizViewModel.moveToNext()
                updateQuestion()
            }
            binding.prevButton.setOnClickListener {
                quizViewModel.moveToPrev()

                updateQuestion()
            }

            binding.cheatButton.setOnClickListener {
                /// Iniciar CheatActivity
                startActivity(intent)

                val answerIsTrue = quizViewModel.currentQuestionAnswer
                val intent = CheatActivity.newIntent(this@MainActivity, answerIsTrue)

                cheatLauncher.launch(intent)
            }


            updateQuestion()
            Log.d(TAG,"Pase por el metodo onCreate")
        Log.d(TAG, "Tengo un QuizViewModel: $quizViewModel")
    }
    private fun updateQuestion (){
        val questionTextResId = quizViewModel.currenQuestionText
        binding.questionTextView.setText(questionTextResId)

    }
    private fun checkAnswer(userAnswer:Boolean){
        val correctAnswer = quizViewModel.currentQuestionAnswer
        val messageResId = when {
            quizViewModel.isCheater -> R.string.judgment_toast
            userAnswer == correctAnswer -> R.string.correct_toast
            else -> R.string.incorrect_toast
        }

        Toast.makeText(this,messageResId, Toast.LENGTH_LONG).show()

        }

    }




