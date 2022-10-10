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
