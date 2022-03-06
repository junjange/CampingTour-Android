package com.junjange.campingtour

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
// 추천 캠핑장 정보를 쓰레드에서 받아 홈 프레그먼트로 가져온다.
var push_camping_next = mutableListOf<String>()

var push_camping_intro_next = mutableListOf<String>() // 소개 글
var push_camping_resveUrl_next = mutableListOf<String>() // 홈페이지
var push_camping_induty_next = mutableListOf<String>() // 홈페이지


// 추천 캠핑장 이미지를 쓰레드에서 받아 홈 프레그먼트로 가져온다.
var push_camping_img_next = mutableListOf<String>()


// 우리 동네 캠핑장 정보를 쓰레드에서 받아 홈 프레그먼트로 가져온다.
var hometown_camping_next = mutableListOf<String>()

// 우리 동네 캠핑장 이미지를 쓰레드에서 받아 홈 프레그먼트로 가져온다.
var hometown_camping_img_next = mutableListOf<String>()

var hometown_camping_intro_next = mutableListOf<String>() // 소개 글
var hometown_camping_resveUrl_next = mutableListOf<String>() // 홈페이지
var hometown_camping_induty_next = mutableListOf<String>() // 홈페이지


var mapX_next = "128.6142847"
var mapY_next = "36.0345423"

var check = 0
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val TAG = "MyHandler"
        val MSG_DO_SOMETHING1 = 1
        val MSG_DO_SOMETHING2 = 2
        val MSG_DO_SOMETHING3 = 3

        val handler = Handler {
            when (it.what) {
                MSG_DO_SOMETHING1 -> {
                    Log.d(TAG, "Do something1")

                    // 추천 캠핑장 쓰레드 생성
                    val Push_Camping_Thread = Thread(Push_Camping_Thread())
                    Push_Camping_Thread.start()
                    Push_Camping_Thread.join() // 멀티 작업 안되게 하려면 start후 join 입력하기
                    Log.d("Ttt", "Taasds")


                }
                MSG_DO_SOMETHING2 -> {
                    Log.d(TAG, "Do something2")

                    // 우리 동네 캠핑장 쓰레드 생성
                    val hometown_Camping_Thread = Thread(hometown_Camping_Thread())
                    hometown_Camping_Thread.start()
                    hometown_Camping_Thread.join() // 멀티 작업 안되게 하려면 start후 join 입력하기

                }
                MSG_DO_SOMETHING3 -> {
                    Log.d(TAG, "Do something3")
                    // 추천 캠핑장 텍스트
                    val push_camping_layout: List<TextView> =
                        listOf(
                           findViewById(R.id.push_camping_text1),
                           findViewById(R.id.push_camping_text2),
                           findViewById(R.id.push_camping_text3),
                           findViewById(R.id.push_camping_text4),
                           findViewById(R.id.push_camping_text5),
                           findViewById(R.id.push_camping_text6),
                           findViewById(R.id.push_camping_text7),
                           findViewById(R.id.push_camping_text8),
                           findViewById(R.id.push_camping_text9),
                           findViewById(R.id.push_camping_text10),
                           findViewById(R.id.push_camping_text11),
                           findViewById(R.id.push_camping_text12),
                           findViewById(R.id.push_camping_text13),
                           findViewById(R.id.push_camping_text14),
                           findViewById(R.id.push_camping_text15),
                           findViewById(R.id.push_camping_text16),
                           findViewById(R.id.push_camping_text17),
                           findViewById(R.id.push_camping_text18),
                           findViewById(R.id.push_camping_text19),
                           findViewById(R.id.push_camping_text20),
                           findViewById(R.id.push_camping_text21),
                           findViewById(R.id.push_camping_text22),
                           findViewById(R.id.push_camping_text23),
                           findViewById(R.id.push_camping_text24),
                        )


                    // 추천 캠핑장 정보를 가져와 텍스트에 뿌려준다.
                    for (i in 0..23){
                        push_camping_layout[i].text = push_camping_next[i]
                    }

                    // 추천 캠핑장 이미지
                    val push_camping_img: List<ImageView> =
                        listOf(
                            findViewById(R.id.push_camping_img1),
                            findViewById(R.id.push_camping_img2),
                            findViewById(R.id.push_camping_img3),
                            findViewById(R.id.push_camping_img4),
                            findViewById(R.id.push_camping_img5),
                            findViewById(R.id.push_camping_img6),
                            findViewById(R.id.push_camping_img7),
                            findViewById(R.id.push_camping_img8),
                        )

                    // 추천 캠핑장 이미지를 뿌려준다.
                    for (i in 0..7){
                        if (push_camping_img_next[i] == ""){
                            push_camping_img_next[i] = "https://blog.kakaocdn.net/dn/NWUde/btrb1w02BQt/2eOMoH8d5nytdVoHUoF6p0/img.png"
                        }

                        Glide.with(this).load(push_camping_img_next[i]).into(push_camping_img[i])
                    }

                    // 우리 동네 캠핑장 텍스트
                    val hometown_camping_layout: List<TextView> =
                        listOf(
                            findViewById(R.id.hometown_camping_text1),
                            findViewById(R.id.hometown_camping_text2),
                            findViewById(R.id.hometown_camping_text3),
                            findViewById(R.id.hometown_camping_text4),
                            findViewById(R.id.hometown_camping_text5),
                            findViewById(R.id.hometown_camping_text6),
                            findViewById(R.id.hometown_camping_text7),
                            findViewById(R.id.hometown_camping_text8),
                            findViewById(R.id.hometown_camping_text9),
                            findViewById(R.id.hometown_camping_text10),
                            findViewById(R.id.hometown_camping_text11),
                            findViewById(R.id.hometown_camping_text12),
                            findViewById(R.id.hometown_camping_text13),
                            findViewById(R.id.hometown_camping_text14),
                            findViewById(R.id.hometown_camping_text15),

                            )


                    // 우리 동네 캠핑장 정보를 가져와 텍스트에 뿌려준다.
                    for (i in 0..14){
                        hometown_camping_layout[i].text = hometown_camping_next[i]
                    }
                    Log.d("tttaaaaa", hometown_camping_next.toString())

                    // 우리 동네  캠핑장 이미지
                    val hometown_camping_img: List<ImageView> =
                        listOf(
                            findViewById(R.id.hometown_camping_img1),
                            findViewById(R.id.hometown_camping_img2),
                            findViewById(R.id.hometown_camping_img3),
                            findViewById(R.id.hometown_camping_img4),
                            findViewById(R.id.hometown_camping_img5),

                            )

                    Log.d("ㅇㅇ",hometown_camping_img_next.toString())

                    // 우리 동네 캠핑장 이미지를 뿌려준다.
                    for (i in 0..4){
                        if (hometown_camping_img_next[i] == ""){
                            hometown_camping_img_next[i] = "https://blog.kakaocdn.net/dn/NWUde/btrb1w02BQt/2eOMoH8d5nytdVoHUoF6p0/img.png"
                        }

                        Glide.with(this).load(hometown_camping_img_next[i]).into(hometown_camping_img[i])
                        Log.d("asdasd",hometown_camping_img_next.toString() )

                    }
                }
            }
            true
        }



        handler.sendEmptyMessage(MSG_DO_SOMETHING1)
        handler.sendEmptyMessageDelayed(MSG_DO_SOMETHING2,1000)
        handler.sendEmptyMessageDelayed(MSG_DO_SOMETHING3,1000)





    }
}
// 추천 캠핑장 쓰레드
// 네트워크를 이용할 때는 쓰레드를 사용해서 접근해야 함
class Push_Camping_Thread: Runnable {
    @RequiresApi(Build.VERSION_CODES.Q)
    @Synchronized
    override fun run() {

        // 키 값
        val key = "키값"


        // 현재 페이지번호
        val pageNo = "&pageNo=1"

        // 한 페이지 결과 수
        val numOfRows ="&numOfRows=8"

        // AND(안드로이드)
        val MobileOS = "&MobileOS=AND"

        // 서비스명 = 어플명
        val MobileApp = "&MobileApp=AppTest"

        val keyword = "&keyword=계곡"

        // API 정보를 가지고 있는 주소
        val site = "http://api.visitkorea.or.kr/openapi/service/rest/GoCamping/searchList?serviceKey="+key+pageNo+numOfRows+MobileOS+MobileApp+keyword+"&_type=json"
        Log.d("보자계곡", site)

        val url = URL(site)
        val conn = url.openConnection()
        val input = conn.getInputStream()
        val isr = InputStreamReader(input)
        // br: 라인 단위로 데이터를 읽어오기 위해서 만듦
        val br = BufferedReader(isr)

        // Json 문서는 일단 문자열로 데이터를 모두 읽어온 후, Json에 관련된 객체를 만들어서 데이터를 가져옴
        var str: String? = null
        val buf = StringBuffer()

        do{
            str = br.readLine()

            if(str != null){

                buf.append(str)
            }
        }while (str != null)


        // 전체가 객체로 묶여있기 때문에 객체형태로 가져옴
        val root = JSONObject(buf.toString())
        val response = root.getJSONObject("response").getJSONObject("body").getJSONObject("items")
        val item = response.getJSONArray("item") // 객체 안에 있는 item이라는 이름의 리스트를 가져옴

        // 추천 캠핑장 리스트
        val push_camping = mutableListOf<String>()

        // 데이터를 가져왔다면 다시 가져가지 않는다.
        if (push_camping_next.size == 0){

            // 페이지 수만큼 반복하여 데이터를 불러온다.
            for(i in 0 until item.length()) {

                // 쪽수 별로 데이터를 읽는다.
                val jObject = item.getJSONObject(i)
                push_camping.add(JSON_Parse(jObject, "facltNm")) // 캠핑장 이름
                push_camping.add(JSON_Parse(jObject, "addr1")) // 지역
                push_camping.add(JSON_Parse(jObject, "tel")) // 전화번호
                push_camping_induty_next.add(JSON_Parse(jObject, "induty")) // 특징?
                push_camping_intro_next.add(JSON_Parse(jObject, "intro")) // 소개 글
                push_camping_resveUrl_next.add(JSON_Parse(jObject, "resveUrl")) // 홈페이지
                push_camping_img_next.add(JSON_Parse(jObject, "firstImageUrl")) // 캠핑장 이미지




            }


            for(j in 0 until item.length() * 3){
                push_camping_next.add(push_camping[j])

            }



        }

    }

    // 함수를 통해 데이터를 불러온다.
    fun JSON_Parse(obj: JSONObject, data : String): String {
        // 원하는 정보를 불러와 리턴받고 없는 정보는 캐치하여 "없습니다."로 리턴받는다.
        return try {
            obj.getString(data)

        } catch (e: Exception) {
            ""
        }
    }
}


// 우리 동네 캠핑장 쓰레드
// 네트워크를 이용할 때는 쓰레드를 사용해서 접근해야 함
class hometown_Camping_Thread: Runnable{
    @RequiresApi(Build.VERSION_CODES.Q)
    @Synchronized
    override fun run() {

        // 키 값
        val key = "키값"

        // 현재 페이지번호
        val pageNo = "&pageNo=1"

        // 한 페이지 결과 수
        val numOfRows ="&numOfRows=10"

        // AND(안드로이드)
        val MobileOS = "&MobileOS=AND"

        // 서비스명 = 어플명
        val MobileApp = "&MobileApp=AppTest"

        // 경도 좌표
        val mapX = "&mapX=$mapX_next"
        Log.d("tttt",mapX_next )
        // 위도 좌표
        val mapY = "&mapY=$mapY_next"
        Log.d("tttt",mapY_next )

        // 거리 반경
        val radius = "&radius=10000"

        // API 정보를 가지고 있는 주소
        val site = "http://api.visitkorea.or.kr/openapi/service/rest/GoCamping/locationBasedList?serviceKey="+key+pageNo+numOfRows+MobileOS+MobileApp+mapX+mapY+radius+"&_type=json"
        Log.d("보자", site)
        val url = URL(site)
        val conn = url.openConnection()
        val input = conn.getInputStream()
        val isr = InputStreamReader(input)
        // br: 라인 단위로 데이터를 읽어오기 위해서 만듦
        val br = BufferedReader(isr)

        // Json 문서는 일단 문자열로 데이터를 모두 읽어온 후, Json에 관련된 객체를 만들어서 데이터를 가져옴
        var str: String? = null
        val buf = StringBuffer()

        do{
            str = br.readLine()

            if(str != null){
                buf.append(str)
            }
        }while (str != null)


        try {
            // 전체가 객체로 묶여있기 때문에 객체형태로 가져옴
            val root = JSONObject(buf.toString())
            val response = root.getJSONObject("response").getJSONObject("body").getJSONObject("items")
            val item = response.getJSONArray("item") // 객체 안에 있는 item이라는 이름의 리스트를 가져옴

            // 우리 동네 캠핑장 리스트
            val hometown_camping = mutableListOf<String>()

            // 데이터를 가져왔다면 다시 가져가지 않는다.
            hometown_camping_next.clear()
            hometown_camping_induty_next.clear()
            hometown_camping_intro_next.clear()
            hometown_camping_resveUrl_next.clear()
            hometown_camping_img_next.clear()

            // 페이지 수만큼 반복하여 데이터를 불러온다.
            for(i in 0 until item.length()) {

                // 쪽수 별로 데이터를 읽는다.
                val jObject = item.getJSONObject(i)
                hometown_camping.add(JSON_Parse(jObject, "facltNm")) // 캠핑장 이름
                hometown_camping.add(JSON_Parse(jObject, "addr1")) // 지역
                hometown_camping.add(JSON_Parse(jObject, "tel")) // 업종
                hometown_camping_induty_next.add(JSON_Parse(jObject, "induty")) // 특징?
                hometown_camping_intro_next.add(JSON_Parse(jObject, "intro")) // 소개 글
                hometown_camping_resveUrl_next.add(JSON_Parse(jObject, "resveUrl")) // 홈페이지
                hometown_camping_img_next.add(JSON_Parse(jObject, "firstImageUrl")) // 캠핑장 이미지


            }


            for(j in 0 until item.length() * 3){
                hometown_camping_next.add(hometown_camping[j])

            }
            Log.d("ttt", hometown_camping.toString())
            Log.d("보자고",hometown_camping_next.toString() )

        }catch (e: Exception){

            for(i in 0..15){
                hometown_camping_next.add("")
            }

            for(i in 0..4){
                hometown_camping_img_next.add("https://blog.kakaocdn.net/dn/NWUde/btrb1w02BQt/2eOMoH8d5nytdVoHUoF6p0/img.png")
            }
            Log.d("TTT", "로또"+e.toString())
        }
    }

    // 함수를 통해 데이터를 불러온다.
    fun JSON_Parse(obj: JSONObject, data : String): String {

        // 원하는 정보를 불러와 리턴받고 없는 정보는 캐치하여 "없습니다."로 리턴받는다.
        return try {

            obj.getString(data)

        } catch (e: Exception) {
            ""
        }
    }
}






