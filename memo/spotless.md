# spotless

## GitHubリポジトリ

https://github.com/diffplug/spotless

## 使用方法

puginの導入

- https://plugins.gradle.org/plugin/com.diffplug.spotless

```groovy
plugins {
    id 'com.diffplug.spotless' version '6.12.0'
}
```

spotlessの各種タスクが有効になるので、個別に設定を記述していく

## 設定

spotless Pluginでは`spotless${対象}${動作}`でタスクが定義されている。

対象は
- (なし) -> フォーマットが設定されている対象全て
- Java -> Javaファイルを対象とする
- Misc -> miscで指定されているファイル

動作は
- check -> フォーマッタを利用して違反があれば表示する
- apply -> 検出された違反に対してフォーマッタをかける
- Diagnose -> spotless自信が正しく設定されているかの診断

またデフォルトではgradleのcheckタスクにspotlessCheckタスクが追加されているので`gradle check`でチェック可能


pluginを導入し、利用したい対象や内容に従ってプロパティを設定する。
設定可能なプロパティは以下のようにExtensionクラスで設定されている。
https://github.com/diffplug/spotless/blob/main/plugin-gradle/src/main/java/com/diffplug/gradle/spotless/SpotlessExtension.java
https://github.com/diffplug/spotless/blob/main/plugin-gradle/src/main/java/com/diffplug/gradle/spotless/JavaExtension.java

```groovy

spotless {
    
    ratchetFrom 'origin/main'

    format 'misc', {
        target '*.gradle'

        trimTrailingWhitespace()
        indentWithTabs()
        endWithNewline()
    }
    java {
        googleJavaFormat('1.8').aosp().reflowLongStrings()
        formatAnnotations()
    }
}

```
代表的なものを以下に示す

| 変数                     | 説明                                          |
|:-----------------------|:--------------------------------------------|
| spotless.ratchetFrom   | 指定したブランチとの差分にのみspotlessのチェックを走らせる           |
| spotless.enforceCheck  | gradle checkタスクにspotless checkタスクを含めるか否かの設定 |
| spotless.ratchetFrom   | 指定したブランチとの差分にのみspotlessのチェックを走らせる           |
| spotless.format        | 指定したformat機能を有効にする                          |
| spotless.format.target | フォーマットの対象となるファイルを指定                         |

またそれぞれのformat配下で有効にするフォーマット設定を記述することができる。