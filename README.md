#PluginGradle
>My using Plugin for Gradle in Kotlin.

>> It just reads all files in directory "src" with *.java and *.kt.
>> Then it counts all methods, classes and lines in these files.
>> The output of task "gradle generateProjectStatistic" is json:

>Example:
~~~
#src/main/kotlin/... .kt

class A()
class C(){
    fun D(){}
}
...
...
#src/main/... .java

public class E{
    public E(){}
    private C(){}
}
~~~

Expected Result:
---
~~~
{
"totalClasses": 3,
"totalMethods": 2,
"totalLines": 8
}
~~~