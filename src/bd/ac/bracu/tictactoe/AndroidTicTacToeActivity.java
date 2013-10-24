package bd.ac.bracu.tictactoe;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AndroidTicTacToeActivity extends Activity {

	private TicTacToeGame mGame;
	private Button mBoardButtons[];
	private TextView mInfoTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_android_tic_tac_toe);

		mBoardButtons = new Button[TicTacToeGame.BOARD_SIZE];
		mBoardButtons[0] = (Button) findViewById(R.id.one);
		mBoardButtons[1] = (Button) findViewById(R.id.two);
		mBoardButtons[2] = (Button) findViewById(R.id.three);
		mBoardButtons[3] = (Button) findViewById(R.id.four);
		mBoardButtons[4] = (Button) findViewById(R.id.five);
		mBoardButtons[5] = (Button) findViewById(R.id.six);
		mBoardButtons[6] = (Button) findViewById(R.id.seven);
		mBoardButtons[7] = (Button) findViewById(R.id.eight);
		mBoardButtons[8] = (Button) findViewById(R.id.nine);

		mInfoTextView = (TextView) findViewById(R.id.information);
		mGame = new TicTacToeGame();
		startNewGame();
	}

	class ButtonClickListener implements View.OnClickListenter {
		int location;

		public ButtonClickListener(int location) {
			this.location = location;
		}

		public void onCLick(View view) {
			if (mBoardButtons[location].isEnabled()) {
				setMove(TicTacToeGame.HUMAN_PLAYER, location);
				int winner = mGame.checkForWinner();
				if (winner == 0) {
					mInfoTextView.setText("It's Android's turn.");
					int move = mGame.getComputerMove();
					setMove(TicTacToeGame.COMPUTER_PLAYER, move);
					winner = mGame.checkForWinner();
				}
				if (winner == 0)
					mInfoTextView.setText("It's your turn.");
				else if (winner == 1)
					mInfoTextView.setText("It's a tie!");
				else if (winner == 2)
					mInfoTextView.setText("You won!");
				else
					mInfoTextView.setText("Android won!");
			}
		}
	}

	private void startNewGame() {
		mGame.clearBoard();

		for (int i = 0; i < mBoardButtons.length; i++) {
			mBoardButtons[i].setText("");
			mBoardButtons[i].setEnabled(true);
			mBoardButtons[i].setOnClickListener(new ButtonClickListener(i));
			mInfoTextView.setText("You go first.");
		}

		mInfoTextView.setText("You go first.");
	}

	private void setMove(char player, int location) {
		mGame.setMove(player, location);
		mBoardButtons[location].setEnabled(false);
		mBoardButtons[location].setText(String.valueOf(player));
		if (player == TicTacToeGame.HUMAN_PLAYER) {
			mBoardButtons[location].setTextColor(Color.rgb(0, 200, 0));
		} else {
			mBoardButtons[location].setTextColor(Color.rgb(200, 0, 0));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.android_tic_tac_toe, menu);
		return true;
	}

}
