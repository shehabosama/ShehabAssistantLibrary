# ShehabAssistant
this the lib is providing you voice assistant to help you
in you code by add the three line of code to configer it 

first thing you need add this line in build.gradle (project)
```
repositories {
        google()
        jcenter()
        maven { url 'https://jitpack.io' }
    }
```

second thing you need add this line to import the library in your code in dependencies

```
 dependencies {
	        implementation 'com.github.shehabosama:ShehabAssistant:2.0'
         }
```

  in you activity  
  
  you should to remove the current extend class (AppCompatActivity) and set the BaseActivity
  
  `public class MainActivity extends  BaseActivity {}`
  
  then you need to add this line to show the recorder floatActionButton to hear what do you say
  
  `replaceFragment(R.id.container, MainFragment.newInstance(this),"voice");`
  now you need first to delete all data cach because this version is local and we want to make sure that the app is clear to use
  so you will write this to line in you Activity
  
  ```
public class MainActivity extends  BaseActivity{

    private MyDbAdapter myDbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDbAdapter = new MyDbAdapter(this);
        myDbAdapter.delete();
      }
}
```
  
