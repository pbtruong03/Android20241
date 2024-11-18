package com.pbtruong03.danhsachsinhvien

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pbtruong03.danhsachsinhvien.databinding.LayoutStudentItemBinding

class StudentAdapter(private val context: MainActivity, val students: List<StudentModel>): RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

  class StudentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val textStudentName: TextView = itemView.findViewById(R.id.text_student_name)
    val textStudentId: TextView = itemView.findViewById(R.id.text_student_id)
  }
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
    val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_student_item,
       parent, false)
    return StudentViewHolder(itemView)
  }
  override fun getItemCount(): Int = students.size

  override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
    val student = students[position]
    holder.textStudentName.text = student.studentName
    holder.textStudentId.text = student.studentId
    holder.itemView.findViewById<ImageView>(R.id.image_edit).setOnClickListener {
      context.showEditStudentDialog(student)
    }
    holder.itemView.findViewById<ImageView>(R.id.image_remove).setOnClickListener {
      context.showDeleteConfirmation(student)
    }
  }
}