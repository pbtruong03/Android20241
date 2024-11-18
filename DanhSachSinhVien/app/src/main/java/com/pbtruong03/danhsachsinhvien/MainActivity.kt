package com.pbtruong03.danhsachsinhvien

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.pbtruong03.danhsachsinhvien.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()
{
  var instance: MainActivity? = null
  lateinit var listStudent: MutableList<StudentModel>
  lateinit var studentAdap: StudentAdapter
  var binding: ActivityMainBinding? = null
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    instance = this
    val students = mutableListOf(
      StudentModel("Nguyễn Minh An", "20240001"),
      StudentModel("Trần Hồng Bảo", "20240002"),
      StudentModel("Lê Thị Cẩm", "20240003"),
      StudentModel("Phạm Văn Duy", "20240004"),
      StudentModel("Đỗ Thu Dung", "20240005"),
      StudentModel("Vũ Thanh Hà", "20240006"),
      StudentModel("Hoàng Bảo Hân", "20240007"),
      StudentModel("Bùi Hoàng Hải", "20240008"),
      StudentModel("Đinh Thị Hồng", "20240009"),
      StudentModel("Nguyễn Thị Khánh", "20240010"),
      StudentModel("Phạm Thị Lan", "20240011"),
      StudentModel("Trần Văn Lâm", "20240012"),
      StudentModel("Lê Thị Mai", "20240013"),
      StudentModel("Vũ Văn Minh", "20240014"),
      StudentModel("Hoàng Ngọc Phương", "20240015"),
      StudentModel("Đỗ Ngọc Quân", "20240016"),
      StudentModel("Nguyễn Thị Thanh", "20240017"),
      StudentModel("Trần Văn Sơn", "20240018"),
      StudentModel("Phạm Văn Tùng", "20240019"),
      StudentModel("Lê Thị Xuân", "20240020")
    )
    listStudent = students
    val studentAdapter = StudentAdapter(this, students)

    findViewById<RecyclerView>(R.id.recycler_view_students).run {
      adapter = studentAdapter
      layoutManager = LinearLayoutManager(this@MainActivity)
    }
    studentAdap = studentAdapter
    findViewById<Button>(R.id.btn_add_new).setOnClickListener {
      showAddStudentDialog()
    }
  }
  @SuppressLint("NotifyDataSetChanged")
  private fun showAddStudentDialog() {
    val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_student, null)
    val builder = AlertDialog.Builder(this)
    builder.setView(dialogView)
      .setTitle("Thêm thông tin sinh viên")
      .setPositiveButton("Thêm") { dialog, _ ->
        val name = dialogView.findViewById<EditText>(R.id.etName).text.toString()
        val id = dialogView.findViewById<EditText>(R.id.etId).text.toString()
        listStudent.add(StudentModel(name, id))
        studentAdap.notifyDataSetChanged()
        dialog.dismiss()
      }
      .setNegativeButton("Hủy") { dialog, _ ->
        dialog.dismiss()
      }
      .create()
      .show()
  }
  fun showEditStudentDialog(student: StudentModel) {
    val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_student, null)
    dialogView.findViewById<EditText>(R.id.etName).setText(student.studentName)
    dialogView.findViewById<EditText>(R.id.etId).setText(student.studentId)

    val builder = AlertDialog.Builder(this)
    builder.setView(dialogView)
      .setTitle("Chỉnh sửa thông tin sinh viên")
      .setPositiveButton("Lưu") { dialog, _ ->
        val index = listStudent.indexOf(student)
        val updatedName = dialogView.findViewById<EditText>(R.id.etName).text.toString()
        val updatedId = dialogView.findViewById<EditText>(R.id.etId).text.toString()
        updateStudent(index, StudentModel(updatedName, updatedId))
        dialog.dismiss()
      }
      .setNegativeButton("Hủy") { dialog, _ ->
        dialog.dismiss()
      }
      .create()
      .show()
  }
  fun updateStudent(index: Int, student: StudentModel){
    listStudent.set(index, student)
    studentAdap.notifyDataSetChanged()
  }
  fun showDeleteConfirmation(student: StudentModel) {
    AlertDialog.Builder(this)
      .setTitle("Xóa thông tin sinh viên")
      .setMessage("Xác nhận xoá thông tin sinh viên: ${student.studentName}?")
      .setPositiveButton("Xoá") { dialog, _ ->
        listStudent.remove(student)
        studentAdap.notifyDataSetChanged()
        showUndoSnackbar(student)
        dialog.dismiss()
      }
      .setNegativeButton("Không") { dialog, _ ->
        dialog.dismiss()
      }
      .create()
      .show()
  }
  fun showUndoSnackbar(deletedStudent: StudentModel) {
    Snackbar.make(findViewById(R.id.main), "${deletedStudent.studentName} đã được xóa", Snackbar.LENGTH_LONG)
      .setAction("Hoàn tác") {
        listStudent.add(deletedStudent)
        listStudent.sortBy { it.studentId }
        studentAdap.notifyDataSetChanged()
      }
      .show()
  }



}