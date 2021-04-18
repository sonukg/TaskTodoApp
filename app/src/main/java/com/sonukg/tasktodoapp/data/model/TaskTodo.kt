package com.sonukg.tasktodoapp.data.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo")
class TaskTodo(
        @PrimaryKey(autoGenerate = true) val id:Long?,
        @ColumnInfo var name: String?,
        @ColumnInfo var desc: String?,
        @ColumnInfo var category: String?,
        @ColumnInfo var todoDate: String?,
   // @ColumnInfo(typeAffinity = ColumnInfo.BLOB) var image:ByteArray

):Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readValue(Long::class.java.classLoader) as? Long,
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(name)
        parcel.writeString(desc)
        parcel.writeString(category)
        parcel.writeString(todoDate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TaskTodo> {
        override fun createFromParcel(parcel: Parcel): TaskTodo {
            return TaskTodo(parcel)
        }

        override fun newArray(size: Int): Array<TaskTodo?> {
            return arrayOfNulls(size)
        }
    }
}