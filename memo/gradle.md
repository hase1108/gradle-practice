# gradle

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

gradleビルドは1つ以上のプロジェクトで構成されており、そのプロジェクトでなにができるかはタスクで定義される
通常、タスクはpluginを適用することで提供される
利用可能なタスクは以下のコマンドで確認できる
```gradle tasks```

タスクとタスクの間は依存性を含ませることができる

## property

Gradleでは、当然build.gradleに変数を定義して参照することも可能だが、外部ファイルに置くことも可能である。
基本的には以下の2つのファイルに置くことが多い

- settings.gradle
- gradle.properties

### settings.gradle

厳密に言うとプロパティのファイルではなく、Groovyで記述可能なファイルになる

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
