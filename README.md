# ShehabAssistant

This  library provids  you voice assistant to help you
in you broject. By adding three line of code to configer it .

First thing you need to add , this line in build.gradle (project)
```
repositories {
        google()
        jcenter()
        maven { url 'https://jitpack.io' }
    }
```

second thing you should to add, this line to import the library, in your code in dependencies

```
 dependencies {
	         implementation 'com.github.shehabosama:ShehabAssistant:2.1'
         }
```

  in your activity  .
  
  you should to remove the current extend class (AppCompatActivity) and replace it with BaseActivity.
  
  `public class MainActivity extends  BaseActivity {}`
  
  Then, you should to add this line to show the recorder floatActionButton to hear what do you say.
  
  `replaceFragment(R.id.container, MainFragment.newInstance(this),"voice");`
  
 Now you need first to delete all cach data because this version is local, and we want to make sure that the app is clear to use
  so you will write this two line in your Activity.
  
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
To add some words and thire answer you need to make list of objects to set your question and answer
like this.

  ```
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
            myDbAdapter.insertData(patternQuestionAnswers);
      }
}
```
  Every item has object and the object has three parameters.
  First one is the ID  "Make sure every item defferint each others".
  Second one is the question "this vesion support just English".
  Third one is the answer .
  Fourth is the key the key is the Action id who will work after say the question , like to say "make a toast" and the key of make a   	   toast is "1"
  we will compair if the object return key greater than 0 , and if there is one will do Action we will explain it more than that
  
  To make action by key you will add some of code to your broject you should to implement from CallBack Interface like this 
  
   `public class MainActivity extends  BaseActivity extends CallBacks {}`
  
  implement the functions which need it 
  
  like this
  
  ```
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
	    myDbAdapter.insertData(patternQuestionAnswers);

      }
    @Override
    public void doAction(int key) {
       
        if (key==1){
            Toast.makeText(MainActivity.this, "hello", Toast.LENGTH_SHORT).show();
        }
    }
    
}
```
Here if you say make toast will check object key if the number is "1" will make a toast
