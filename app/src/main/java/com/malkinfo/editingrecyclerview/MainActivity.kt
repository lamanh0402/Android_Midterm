package com.malkinfo.editingrecyclerview

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.Color.red
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.malkinfo.editingrecyclerview.model.ProvinceInfo
import com.malkinfo.editingrecyclerview.view.ProvinceAdapter
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
//    private lateinit var addsBtn: FloatingActionButton
    private lateinit var recv: RecyclerView
//    private lateinit var userList: ArrayList<ProvinceInfo>
    private lateinit var provinceAdapter: ProvinceAdapter
    private lateinit var imgDTinh: ImageView

    val REQUEST_CODE: Int = 100
    private var provinceList = arrayListOf(
        ProvinceInfo(
            "Trường THPT Lương Thế Vinh",
            R.drawable.img3,
            "Ba Đồn, Quảng Bình",
            "Không chỉ đứng trong tốp đầu của tỉnh về chất lượng giáo dục, những năm qua Trường THPT Lương Thế Vinh (TX. Ba Đồn) còn được biết đến là lá cờ đầu trong phong trào toàn dân bảo vệ ANTQ với nhiều cách làm hay, sáng tạo, thiết thực lôi cuốn được đông đảo cán bộ, giáo viên và học sinh tham gia, qua đó tạo ra môi trường sư phạm thân thiện, văn minh, hiện đại đáp ứng yêu cầu ngày càng cao trong công tác dạy và học tại nhà trường."

        ),
        ProvinceInfo(
            "Trường THPT Trần Hưng Đạo ",
            R.drawable.img2,
            "Lệ Thủy, Quảng Bình",
            "Trường THPT Trần Hưng Đạo được thành lập từ năm 1999 theo quyết định số: 896/QĐ-UB ngày 14 tháng 6 năm 1999 của Ủy ban nhân tỉnh Quảng Bình.\n" +
                    "Trường được tách ra từ trường Phổ thông cấp 2+3 Tân Thủy và được đóng trên mảnh đất truyền thống cách mạng thuộc xã Hưng Đạo cũ. Trường được mang tên THPT Trần Hưng Đạo.\n" +
                    "Ngày 01 tháng 9 năm 2001 Trường THPT Trần Hưng Đạo bắt đầu hoạt động. Đây là bước ngoặt đánh dấu sự phát triển đi lên về mọi mặt của nhà trường. Trường trở thành một trong những điểm sáng văn hóa của giáo dục tỉnh Quảng Bình.\n" +
                    "Trường THPT Trần Hưng Đạo nằm về phía Đông – Nam của huyện, trên địa bàn xã Hưng Thủy, huyện Lệ Thủy, tỉnh Quảng Bình."

        ),
        ProvinceInfo(
            "Trường THPT Nguyễn Chí Thanh",
            R.drawable.img6,
            "Huế",
            "Trường THPT Nguyễn Chí Thanh được thành lập vào tháng 8 năm 1988 nhằm đáp ứng nhu cầu học tập của một phần học sinh quận Tân Bình, một địa bàn có tốc độ phát triển dân cư hết sức nhanh chóng lúc bấy giờ. Khi mới thành lập, địa điểm nhà trường nằm tạm trong một khu vực doanh trại quân đội (hiện nay là khuôn viên của trường THCS Hoàng Hoa Thám)."
        ),
        ProvinceInfo(
            "Trường THPT Bắc Kiến Xương",
            R.drawable.img4,
            "Thái Bình",
            "Hơn 10 năm trở lại đây, chất lượng giáo dục toàn diện, kết quả thi học sinh giỏi văn hóa và thể thao cấp tỉnh, thi tốt nghiệp THPT, thi tuyển sinh đại học của học sinh nhà trường luôn nằm trong tốp 10 trường dẫn đầu của tỉnh. Ðặc biệt, 3 năm liên tục 2009, 2010, 2011 Trường được xếp vào tốp 200 trường THPT có điểm thi đại học cao nhất toàn quốc."
        ),
        ProvinceInfo(
            "Trường THPT Thu Xà",
            R.drawable.img5,
            "Quảng Nam",
            "Trường THPT Thu Xà – Quảng Ngãi nằm trên địa bàn Xóm 1, Thôn Thu Xà, Xã Nghĩa Hòa, Huyện Tư Nghĩa, theo đường tỉnh lộ đi về hướng Đông cách thành phố Quảng Ngãi 10km.\n" +
                "\n" +
                " Được thành lập năm 1962, là trường Trung học công lập thứ hai trên địa bàn tỉnh Quảng Ngãi. Những năm học đầu tiên, trường đóng tại Xóm 1, Ấp Phước Long, Xã Tư Thành, Quận Tư Nghĩa (nay là địa điểm Trường Tiểu học Tây Hòa, Xã Nghĩa Hòa). Cuối năm 1965 khi ta mở rộng vùng giải phóng đến các xã miền Đông, huyện Tư Nghĩa, chính quyền Mỹ – Ngụy phải chuyển địa điểm Trường về Xã Tư Duy (nay là Thị trấn La Hà) gần quận đường Tư Nghĩa, nơi Mỹ – Ngụy tập trung quân để dễ quản lý."
        ),
        ProvinceInfo(
            "Trường THPT Phan Chu Trinh",
            R.drawable.img1,
            "Đà Nẵng",
            "Trường Trung học phổ thông (THPT) Phan Châu Trinh đã gắn liền với hai mốc thời gian lịch sử: trước và sau năm 1975. Trước 1975, trong 23 năm, ngôi trường bước trên chặng đường đầy biến động của hoàn cảnh đất nước bị chia cắt cùng với cuộc kháng chiến chống giặc ngoại xâm. Sau 1975, ngôi trường lớn lên với thời kỳ thống nhất, xây dựng và phát triển đất nước của tỉnh Quang Nam – Đà Nẵng và thành phố Đà Nẵng trực thuộc Trung ương (ngày 01/01/1997)."
        ),
        ProvinceInfo(
            "Trường THPT Hà Huy Tập",
            R.drawable.img7,
            "Nha Trang",
            " Ngày 29/8/1977, Ty Giáo dục tỉnh Phú Khánh (nay là tỉnh Khánh Hòa) đã ký quyết định số 843-QĐ/GDTC tách cấp 2 của trường cấp 2, 3 Hà Huy Tập ra khỏi trường và đổi tên thành trường Phổ thông cấp III Hà Huy Tập, trường vinh dự mang tên cố Tổng Bí thư của Đảng Cộng sản Việt Nam."
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recv = findViewById(R.id.mRecycler)

        provinceAdapter = ProvinceAdapter(this, provinceList)
        recv.layoutManager = LinearLayoutManager(this)
        recv.adapter = provinceAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_about -> {
            Toast.makeText(this, "Đây Là Ứng Dụng Về Danh Sách Trường THPT ", Toast.LENGTH_SHORT)
                .show()
            true
        }
        R.id.action_changBackground -> {
            val rnd = Random
            val color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
            recv.setBackgroundColor(color)
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }



//    private fun changeImage() {
//        val intent = Intent(Intent.ACTION_PICK)
//        intent.type = "image/*"
//        startActivityForResult(intent, REQUEST_CODE)
//    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
                imgDTinh.setImageURI(data?.data)
        }
    }

    private fun checkCB(): Int {

        return R.drawable.img1
    }
    /**ok now run this */


}