package com.example.XieChengHomeTry;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;
import lib2.AfterEndAnimFinish;
import lib2.ZoomAnim;


public class MyActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        ZoomAnim.addAnimFor(findViewById(R.id.llBuilding), 0.9f, 300, new AfterEndAnimFinish() {

                    @Override
                    public void doSomething() {

                        Toast.makeText(MyActivity.this, "酒店", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        ZoomAnim.addAnimFor(findViewById(R.id.llPlane), 0.9f, 300, new AfterEndAnimFinish() {

                    @Override
                    public void doSomething() {

                        Toast.makeText(MyActivity.this, "机票", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        ZoomAnim.addAnimFor(findViewById(R.id.llFlag), 0.9f, 300, new AfterEndAnimFinish() {

                    @Override
                    public void doSomething() {

                        Toast.makeText(MyActivity.this, "旅游团", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        ZoomAnim.addAnimFor(findViewById(R.id.llCar), 0.9f, 300, new AfterEndAnimFinish() {

                    @Override
                    public void doSomething() {

                        Toast.makeText(MyActivity.this, "用车", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        ZoomAnim.addAnimFor(findViewById(R.id.llTags), 0.9f, 300, new AfterEndAnimFinish() {

                    @Override
                    public void doSomething() {

                        Toast.makeText(MyActivity.this, "团购", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        ZoomAnim.addAnimFor(findViewById(R.id.llUmbrella), 0.3f, 300, new AfterEndAnimFinish() {

                    @Override
                    public void doSomething() {

                        Toast.makeText(MyActivity.this, "卖伞", Toast.LENGTH_SHORT).show();
                    }
                }
        );

    }
}
