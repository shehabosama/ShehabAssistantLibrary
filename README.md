# ShehabAssistant

This lib is providing to you a voice assistant to help you
in you code by adding the three lines of code to configer it 

First thing you need to add this line in build.gradle (project)
```
repositories {
        google()
        jcenter()
        maven { url 'https://jitpack.io' }
    }
```

Second thing you need to add this line to import the library in your code in dependencies

```
 dependencies {
	        implementation 'com.github.shehabosama:ShehabAssistant:2.2'
         }
```

  In your activity  
  
  You should to remove the current extend class that called (AppCompatActivity) and set the BaseActivity Class
  
  `public class MainActivity extends  BaseActivity {}`
  
  Then you need to add this line to show the floatActionButton in your layout that will be help you to connect to the voice assitant 
  and with long click it will start to  hear what do you say
  
  `replaceFragment(R.id.container, MainFragment.newInstance(this),"voice");`
  
  now you need first to delete all cash data because this version is local and we want to make sure that the app is clear to use
  so you will write this two line in your Activity
  
  ```java
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
To add some questions and their answers you need to make list of objects to set your question and answer
like this

  ```java
public class MainActivity extends  BaseActivity {

    private MyDbAdapter myDbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDbAdapter = new MyDbAdapter(this);
        myDbAdapter.delete();
	
	    List<PatternQuestionAnswer> patternQuestionAnswers = new ArrayList<>();
            patternQuestionAnswers.add(new PatternQuestionAnswer(1,"hello","hello dear",0));
            patternQuestionAnswers.add(new PatternQuestionAnswer(2,"hey","What do you need",0));
	    myDbAdapter.insert(patternQuestions);
           
      }
}
```
  Every item has object and the object has three parameters.<br/>
  First one is the ID  "make sure every item differents each others".<br/>
  Second one is the question "this version support just english".<br/>
  Third one is the answer .<br/>
  Fourth is the key the key is the Action id who will do something after say the question , like to say "make a toast" and the key is "1"
  we will compare if the object return key greater than 0 if it is greater than 0 will do an Action .
  
  To make action by key you will add some of code to your project , you should implement from CallBack Interface like this 
  
   ```java public class MainActivity extends  BaseActivity implements CallBacks {}```
  
  Implement the functions that belong to this interface that it is need it 
  
  like this : 
  
  ```java
public class MainActivity extends  BaseActivity extends CallBacks{

    private MyDbAdapter myDbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDbAdapter = new MyDbAdapter(this);
        myDbAdapter.delete();
	
	    List<PatternQuestionAnswer> patternQuestionAnswers = new ArrayList<>();
            patternQuestionAnswers.add(new PatternQuestionAnswer(1,"hello","hello dear",0));
            patternQuestionAnswers.add(new PatternQuestionAnswer(2,"hey","What do you need",0));
            patternQuestionAnswers.add(new PatternQuestionAnswer(8,"make toast","okay i will make a toast",1));
            myDbAdapter.insert(patternQuestions);
      }
    @Override
    public void doAction(int key) {
       
        if (key==1){
            Toast.makeText(MainActivity.this, "hello", Toast.LENGTH_SHORT).show();
        }
    }
    
}
```
here if you say make toast will check object key if the number is "1" will make toast
