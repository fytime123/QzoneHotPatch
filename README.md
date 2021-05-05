## Android热修复技术——QQ空间补丁方案
This project is an implementation of Hotfix solution proprosed by Q-Zone. For more details, please refer to my Blogs:[Android热修复技术——QQ空间补丁方案解析](https://yq.aliyun.com/articles/70320?spm=5176.100239.blogcont70321.17.CjiJTQ)

> 编译步骤：
* 1.先项目build.gradle,中先注释掉classpath 'com.hot.plugin:hot:1.0.0'
* 2.app Module中build.gradle先注释掉apply plugin: 'com.hot.plugin'
* 3.Gradle中 hack Module 运行Tasks中build的assemble
* 4.Gradle中  hotplugin Module 运行Tasks中publishing的publish
* 5.把步骤1和步骤2中的注释打开

>这样就可以运行出apk了，利用Android Studio中Build的Analyze Apk分析apk,查看Calculator.java类的字节码
可以看到   const-class v1, Lcom/hotpatch/hack/AntilazyLoad;已经被注入进去了。这样整个热修复流程就可以实现了。

#### Calculator.java类的字节码：

```java
.class public Lcom/hotpatch/demo/Calculator;
.super Ljava/lang/Object;
.source "Calculator.java"


# direct methods
.method public constructor <init>()V
    .registers 3

    .line 7
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .end local p0    # "this":Lcom/hotpatch/demo/Calculator;
    sget-object v0, Ljava/lang/System;->out:Ljava/io/PrintStream;

    const-class v1, Lcom/hotpatch/hack/AntilazyLoad;

    invoke-virtual {v0, v1}, Ljava/io/PrintStream;->println(Ljava/lang/Object;)V

    return-void
.end method


# virtual methods
.method public calculate()F
    .registers 2

    .line 9
    const/4 v0, 0x1

    div-int/lit8 v0, v0, 0x0

    int-to-float v0, v0

    return v0
.end method
```