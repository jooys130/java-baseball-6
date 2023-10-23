package baseball;

import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        OutputView.startGame();
        playBasketBallGame();
    }

    public static void playBasketBallGame() {
        AnswerNumberGenerator answerNumberGenerator = new AnswerNumberGenerator();
        List<Integer> answerNumberList = answerNumberGenerator.getAnswerNumberList();

        while (true) {
            String userInputNumber = InputView.getInputNumber();
            try {
                Validator.validateUserInputNumber(userInputNumber);
            } catch (IllegalArgumentException e) {
                System.out.println("IllegalArgumentException: " + e.getMessage());
                return;
            }

            List<Integer> userInputNumberList = new ArrayList<>();
            for (char digit : userInputNumber.toCharArray()) {
                userInputNumberList.add(Character.getNumericValue(digit));
            }

            int[] strikeAndBallCount = getStrikeAndBallCount(answerNumberList, userInputNumberList);
            OutputView.showGameResult(strikeAndBallCount);

            if (strikeAndBallCount[0] == Constants.NUMBER_SIZE) {
                OutputView.finishGame();
                return;
            }
        }
    }

    public static int[] getStrikeAndBallCount(List<Integer> answerNumber,
        List<Integer> userNumber) {

        int[] strikeAndBallCount = new int[2];

        for (int index = 0; index < Constants.NUMBER_SIZE; index++) {
            if (answerNumber.get(index) == userNumber.get(index)) {
                strikeAndBallCount[0]++;
                continue;
            }
            if (answerNumber.contains(userNumber.get(index))) {
                strikeAndBallCount[1]++;
            }
        }

        return strikeAndBallCount;
    }
}