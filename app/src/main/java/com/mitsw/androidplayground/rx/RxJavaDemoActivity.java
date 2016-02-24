package com.mitsw.androidplayground.rx;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.mitsw.androidplayground.R;
import com.mitsw.androidplayground.utils.log.RxSubjectHelper;

public class RxJavaDemoActivity extends AppCompatActivity {

    TextView mOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_demo);


        initView();
        practiceRxJava();
    }

    private void initView(){
        mOutput = (TextView)findViewById(R.id.tvOutput);
    }

    public void practiceRxJava() {

//        Observable.just("Hello World!").map(s -> s + " CMS")
//                .subscribe(s -> {
//                    System.out.println(">>>>>>>>>>>>>>>>>>> s:" + s);
//                });


//        List<Integer> integers = new ArrayList<>();
//        integers.add(1);
//        integers.add(2);
//        integers.add(3);
//        integers.add(4);
//        integers.add(5);
//        integers.add(6);
//        integers.add(7);
//        integers.add(8);
//        integers.add(9);
//        integers.add(10);

//        Observable.from(integers)
//                .subscribeOn(Schedulers.newThread())
//                .map(integer -> {
//                    System.out.println(">>>>>>>>>>>>>>>>>>> Invoked 1 thread id:" + Thread.currentThread().getId());
//                    return integer + 10;
//                })
//                .subscribeOn(Schedulers.computation())
//                .map(integer -> {
//                    System.out.println(">>>>>>>>>>>>>>>>>>> Invoked 2 thread id:" + Thread.currentThread().getId());
//                    return integer + 11;
//                })
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(integer -> {
//                    System.out.println(">>>>>>>>>>>>>>>>>>> receive thread id:" + Thread.currentThread().getId());
//                    mOutput.setText(integer.toString());
//
//                });

//        Observable.just("Hello World!")
//                .flatMap(urls -> Observable.just(urls))
//                .filter(title -> title != null)
//                .take(5)
//                .subscribe(title -> System.out.println(title));

        RxSubjectHelper.instanceOf().setString("hello from rxjava");

//        Observable.just("http://cms.gitbooks.io/feed/content/RxJava.html")
//                .map(url -> download(url))
//                .subscribeOn(Schedulers.io())              // 在 io Thread() 跑
//                .observeOn(AndroidSchedulers.mainThread()) // 在 mainThread 回來印
//                .subscribe(System.out::println);

    }
}
