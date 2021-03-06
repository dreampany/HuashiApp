package net.muxi.huashiapp.common.data;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

/**
 * Created by ybao on 16/7/30.
 */
public class Course implements Parcelable {

    public String id;
    public String course;
    public String teacher;
    public String weeks;
    public String day;
    public int start;
    public int during;
    public String place;
    public String remind;
    public int color;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.course);
        dest.writeString(this.teacher);
        dest.writeString(this.weeks);
        dest.writeString(this.day);
        dest.writeInt(this.start);
        dest.writeInt(this.during);
        dest.writeString(this.place);
        dest.writeString(this.remind);
        dest.writeInt(this.color);
    }

    public Course() {
    }

    protected Course(Parcel in) {
        this.id = in.readString();
        this.course = in.readString();
        this.teacher = in.readString();
        this.weeks = in.readString();
        this.day = in.readString();
        this.start = in.readInt();
        this.during = in.readInt();
        this.place = in.readString();
        this.remind = in.readString();
        this.color = in.readInt();
    }

    public static final Creator<Course> CREATOR = new Creator<Course>() {
        @Override
        public Course createFromParcel(Parcel source) {
            return new Course(source);
        }

        @Override
        public Course[] newArray(int size) {
            return new Course[size];
        }
    };

    public boolean hasNullValue() {
        if (TextUtils.isEmpty(id)
                || TextUtils.isEmpty(course)
                || TextUtils.isEmpty(teacher)
                || TextUtils.isEmpty(weeks)
                || TextUtils.isEmpty(day)
                || TextUtils.isEmpty(place)
                || TextUtils.isEmpty(remind)
                ) {
            return true;
        }
        return false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getWeeks() {
        return weeks;
    }

    public void setWeeks(String weeks) {
        this.weeks = weeks;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getDuring() {
        return during;
    }

    public void setDuring(int during) {
        this.during = during;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getRemind() {
        return remind;
    }

    public void setRemind(String remind) {
        this.remind = remind;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public static Creator<Course> getCREATOR() {
        return CREATOR;
    }
}
