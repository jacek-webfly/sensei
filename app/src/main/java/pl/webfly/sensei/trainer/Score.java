package pl.webfly.sensei.trainer;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.DecimalFormat;

public class Score implements Parcelable {
    public static final String INTENT_NAME = "score";
    public static final int SCORE_DECIMAL_PRECISION = 1;
    private int answeredQuestionQnt;
    private int correctAnswersQnt;

    public Score(int answeredQuestionQnt, int correctAnswersQnt) {
        this.answeredQuestionQnt = answeredQuestionQnt;
        this.correctAnswersQnt = correctAnswersQnt;
    }

    protected Score(Parcel in) {
        answeredQuestionQnt = in.readInt();
        correctAnswersQnt = in.readInt();
    }

    public static final Creator<Score> CREATOR = new Creator<Score>() {
        @Override
        public Score createFromParcel(Parcel in) {
            return new Score(in);
        }

        @Override
        public Score[] newArray(int size) {
            return new Score[size];
        }
    };

    public float getPercentageScore() {
        if (correctAnswersQnt == 0) {
            return 0;
        } else {
            float result = (float) correctAnswersQnt / answeredQuestionQnt * 100;
            DecimalFormat twoDForm = new DecimalFormat(
                    "#." + new String(new char[SCORE_DECIMAL_PRECISION]).replace("\0", "#"));
            return Float.valueOf(twoDForm.format(result));
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(answeredQuestionQnt);
        dest.writeInt(correctAnswersQnt);
    }

    public int getAnsweredQuestionQnt() {
        return answeredQuestionQnt;
    }

    public int getCorrectAnswersQnt() {
        return correctAnswersQnt;
    }
}
