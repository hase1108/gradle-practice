# gradle

## ディレクトリ構成

https://docs.gradle.org/current/userguide/directory_layout.html#dir:project_root

## 通知

ビルドスクリプトではビルドが進行した様々なタイミングで通知を受け取る事ができる。
通知は特定のリスナーインターフェースを実装するか、通知が発生したときに実行するクロージャを提供することで実現できる。

### プロジェクト評価

評価…ビルドフェーズにおける構成フェーズを実行し終え、次のフェーズに移れるかどうかの評価のようなニュアンスの認識。そのため構成フェーズで設定を行うプロパティなどは評価後でないと参照できない

- before/afterEvaluate プロジェクトの評価の前後で通知を受け取り操作を行うことができる。
  評価後であれば、各プロパティで定義されているプロパティなどにアクセスすることができるので、追加の構成やカスタムのログ記録やプロファイルを行うことが出来る。
  なおこちらは評価が正常終了していないと実行されない

- afterProject プロジェクト評価後に必ず実行される
  評価が仮に失敗しても実行される

afterProjectとafterEvaluateがどちらも定義されている場合はafterProjectのほうが先に実行される

### タスク作成時の通知

タスクがプロジェクトに追加された直後に通知を受け取る事ができる。
ここでプロパティを設定したり動作を追加することも可能

### タスク実行時の通知

以下のイベント時にも通知を受け取ることができる

- タスク実行グラフ準備完了時
- 各タスクの実行/終了時
  →Deplicatedでver8.0で削除予定なので使わない、以下のビルドサービスを構築して同様のことをした方が良いとのこと

https://docs.gradle.org/current/userguide/build_services.html#build_services

## toolchain

参考: https://blog.gradle.org/java-toolchains
参考: https://docs.gradle.org/current/userguide/toolchains.html

定義したjavaバージョンで各種のタスクを実行してくれるし、指定したjavaのjdkがない場合はダウンロードして勝手に設定してくれる
jdkの探索は以下の順番で行われる

1. ローカル
2. gradleが探してくるjdk(vendor = JvmVendorSpecで指定可能)
3. AdoptOpenJDK

## task

参考: https://qiita.com/opengl-8080/items/c482998fa15ce738e2ba
参考: https://docs.gradle.org/current/userguide/tutorial_using_tasks.html
参考: https://www.baeldung.com/gradle-custom-task
参考: https://qiita.com/opengl-8080/items/0a192b62ee87d8ac7578#%E5%89%8D%E6%8F%90%E7%9F%A5%E8%AD%98
参考: https://docs.gradle.org/current/userguide/custom_tasks.html#custom_tasks
参考: https://docs.gradle.org/current/userguide/more_about_tasks.html#more_about_tasks

gradleビルドは1つ以上のプロジェクトで構成されており、そのプロジェクトでなにができるかはタスクで定義される
通常、タスクはpluginを適用することで提供される
利用可能なタスクは以下のコマンドで確認できる
```gradle tasks```

タスクとタスクの間は依存性を含ませることができる
タスクはinitialization/Configuration/Executionの各フェーズで別れており、Executionで行われるのはActionとして定義されるもので、それ以外は設定フェーズで実行される
設定フェーズに指定されているものは、指定しているタスクに関わらず実行されてしまうので注意

タスクは同じプロジェクト内であれば、タスク名だけで参照可能である

## property

Gradleでは、当然build.gradleに変数を定義して参照することも可能だが、外部ファイルに置くことも可能である。
基本的には以下の2つのファイルに置くことが多い

- settings.gradle
- gradle.properties

### settings.gradle

参考: https://docs.gradle.org/current/userguide/custom_tasks.html

厳密に言うとプロパティのファイルではなく、Groovyで記述可能なファイルになる
Settingオブジェクトに対する操作を設定することができる

### gradle.properties

以下の場所に定義可能で、先勝ちで設定される

- GRADLE_USER_HOMEディレクトリ内
- プロジェクトのルートディレクトリ
- Gradleのインストールディレクトリ

基本的にはプロジェクトのルートディレクトリにファイルを配置することが多い
プロパティは、通常のpropertiesファイルと同様に、全て文字列として解釈され、build.gradleでは以下のような形式で参照可能になる

- key
- System.properties['key']
- ${"key"}

### best practice

参考: https://docs.gradle.org/current/userguide/authoring_maintainable_build_scripts.html#authoring_maintainable_build_scripts

> - スクリプトで命令型ロジック(条件判定を含むロジック)を持たず、pluginとして外出しする

- スクリプトでは宣言型のコードを書く
- buildSrcなどに命令型ロジックを置いとくと言い

> - タスク タイプは、タスク名の後の括弧内の唯一のキーと値のペアである必要があります。
> - その他の構成は、タスクの構成ブロック内で行う必要があります。
> - タスクを宣言するときに追加されるタスク アクションは、メソッドTask.doFirst{}またはTask.doLast{}でのみ宣言する必要があります。
> - アドホック タスク (明示的な型を持たないタスク) を宣言する場合、1 つのアクションのみを宣言する場合は、Task.doLast{}を使用する必要があります。
> - タスクはグループと説明を定義する必要があります。

> - 構成段階で実行されるロジックを最小限に抑える

## build script

参考:https://docs.gradle.org/current/userguide/writing_build_scripts.html

### オプション

`-q`オプションでgradleのログ出力を抑制してタスクによる出力のみひょうじされるようになる

### 変数

#### ローカル変数

- def を用いて定義できる

### 拡張プロパティ

Gradleで設定されている各種オブジェクトに対して、プログラマ側で拡張のプロパティを設定させることが出来る。
この時、設定したオブジェクトにアクセスできる場合はプロパティにもアクセス出来るため、通常ローカル変数よりもアクセス出来る範囲が大きくなる。

### gradle/kotolinの力を使える

gradleで記述可能なので当然種々のメソッドを利用できる

```aidl
tasks.register('upper') {
    doLast {
        String someString = 'mY_nAmE'
        println "Original: $someString"
        println "Upper case: ${someString.toUpperCase()}"
    }
}
```