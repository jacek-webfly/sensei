package pl.webfly.sensei.trainer;

import android.os.Parcel;
import android.os.Parcelable;

public class TrainerParams implements Parcelable {
    public static final String INTENT_NAME = "params";
    private int questionLimit;
    private int numberOfReplies;
    private TrainingTypes trainingType;

    public enum TrainingTypes {PREDICT, GUESS}

    public TrainerParams(int questionLimit, int numberOfReplies, TrainingTypes trainingType) {
        this.questionLimit = questionLimit;
        this.numberOfReplies = numberOfReplies;
        this.trainingType = trainingType;
    }

    public int getQuestionLimit() {
        return questionLimit;
    }

    public int getNumberOfReplies() {
        return numberOfReplies;
    }

    public TrainingTypes getTrainingType() {
        return trainingType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.questionLimit);
        dest.writeInt(this.numberOfReplies);
        dest.writeInt(this.trainingType == null ? -1 : this.trainingType.ordinal());
    }

    protected TrainerParams(Parcel in) {
        this.questionLimit = in.readInt();
        this.numberOfReplies = in.readInt();
        int tmpTrainingType = in.readInt();
        this.trainingType = tmpTrainingType == -1 ? null : TrainingTypes.values()[tmpTrainingType];
    }

    public static final Creator<TrainerParams> CREATOR = new Creator<TrainerParams>() {
        @Override
        public TrainerParams createFromParcel(Parcel source) {
            return new TrainerParams(source);
        }

        @Override
        public TrainerParams[] newArray(int size) {
            return new TrainerParams[size];
        }
    };
}
