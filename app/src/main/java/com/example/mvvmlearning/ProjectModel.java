package com.example.mvvmlearning;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "project")
public class ProjectModel implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    public int pId;

    @ColumnInfo(name = "p_title")
    public String title;
    public String language;
    public int watcher;
    public int issues;

    public ProjectModel() {
    }

    protected ProjectModel(Parcel in) {
        pId = in.readInt();
        title = in.readString();
        language = in.readString();
        watcher = in.readInt();
        issues = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(pId);
        dest.writeString(title);
        dest.writeString(language);
        dest.writeInt(watcher);
        dest.writeInt(issues);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ProjectModel> CREATOR = new Creator<ProjectModel>() {
        @Override
        public ProjectModel createFromParcel(Parcel in) {
            return new ProjectModel(in);
        }

        @Override
        public ProjectModel[] newArray(int size) {
            return new ProjectModel[size];
        }
    };
}
