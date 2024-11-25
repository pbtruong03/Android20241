package com.pbtruong03.studentmanager;

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var studentListView: ListView
    private var studentList: MutableList<Student> = mutableListOf()

    companion object {
        private const val ADD_STUDENT_REQUEST = 1
        private const val EDIT_STUDENT_REQUEST = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        studentListView = findViewById(R.id.studentListView)

        // Sử dụng adapter tùy chỉnh
        val adapter = StudentAdapter(this, R.layout.student_item, studentList)
        studentListView.adapter = adapter

        // Đăng ký context menu cho ListView
        registerForContextMenu(studentListView)

        // Kiểm tra và hiển thị danh sách sinh viên
        loadStudentData(adapter)
    }

    private fun loadStudentData(adapter: StudentAdapter) {
        // Thêm dữ liệu mẫu vào danh sách
        studentList.addAll(getStudentData())
        adapter.notifyDataSetChanged()
    }

    private fun getStudentData(): List<Student> {
        return listOf(
            Student("Nguyen Van An", "20220001"),
            Student("Tran Thi Binh", "20220002"),
            Student("Le Van Cuong", "20220003"),
            Student("Pham Thi Dao", "20220004"),
            Student("Nguyen Hoang Hai", "20220005"),
            Student("Tran Minh Chau", "20220006"),
            Student("Le Thi Dieu", "20220007"),
            Student("Vu Van Duong", "20220008"),
            Student("Nguyen Thi Hoa", "20220009"),
            Student("Pham Minh Hieu", "20220010"),
            Student("Tran Thi Kim", "20220011"),
            Student("Nguyen Van Khang", "20220012"),
            Student("Le Hoang Lam", "20220013"),
            Student("Nguyen Thi My", "20220014"),
            Student("Tran Van Phuc", "20220015"),
            Student("Pham Thi Quynh", "20220016"),
            Student("Nguyen Van Son", "20220017"),
            Student("Tran Hoang Thao", "20220018"),
            Student("Le Thi Trang", "20220019"),
            Student("Vu Minh Tuan", "20220020"),
            Student("Nguyen Hoang Uyen", "20220021"),
            Student("Pham Thi Van", "20220022"),
            Student("Tran Minh Vu", "20220023"),
            Student("Le Thi Xuan", "20220024"),
            Student("Nguyen Thanh Yen", "20220025")
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add_new -> {
                val intent = Intent(this, AddStudentActivity::class.java)
                startActivityForResult(intent, ADD_STUDENT_REQUEST)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            if (requestCode == ADD_STUDENT_REQUEST) {
                val newStudent = data?.getParcelableExtra<Student>("newStudent")
                newStudent?.let {
                    studentList.add(it)
                    val adapter = studentListView.adapter as StudentAdapter
                    adapter.notifyDataSetChanged()
                }
            }
            if (requestCode == EDIT_STUDENT_REQUEST) {
                val editedStudent = data?.getParcelableExtra<Student>("editedStudent")
                editedStudent?.let {
                    val index = studentList.indexOfFirst { it.studentId == editedStudent.studentId }
                    if (index != -1) {
                        studentList[index] = it
                        val adapter = studentListView.adapter as StudentAdapter
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }

    // Context menu - Hiển thị khi nhấn lâu vào một sinh viên
    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context_menu, menu)
    }

    // Xử lý khi người dùng chọn một mục trong context menu
    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val selectedStudent = studentList[info.position]

        return when (item.itemId) {
            R.id.edit -> {
                // Mở Activity để sửa thông tin sinh viên
                val intent = Intent(this, EditStudentActivity::class.java)
                intent.putExtra("student", selectedStudent)
                startActivityForResult(intent, EDIT_STUDENT_REQUEST)
                true
            }
            R.id.remove -> {
                // Xóa sinh viên khỏi danh sách
                studentList.removeAt(info.position)
                val adapter = studentListView.adapter as StudentAdapter
                adapter.notifyDataSetChanged()
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }
}
