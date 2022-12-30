# Running Gradle

## taskの実行

タスクを指定して通常実行
```shell
gradle :myTask
gradle myTask
```
### オプションの指定

```shell
# ビルトインオプション
gradle --${option}=${value} ${Task}

# タスクオプション
gradle ${Task} --${option}=${value}

# オプションは以下の様に=をつけなくても良いが、付けた方が良い
gradle ${Task} --${option} ${value}

# ビルトインオプションとタスクオプションを併用する場合
# ハイフンをオプションとタスク名の間に入れる
gradle --${option}=${value} -- ${Task} --${option}=${value}
```

### マルチプロジェクトビルドにおけるタスク実行

```shell
# サブプロジェクトに対してタスク実行
gradle :${subProjectName}:${taskName}
gradle ${subProjectName}:${taskName}

# ルートプロジェクトで実行することで全てのサブプロジェクトに対してタスク実行可能
gradle ${taskName}
# サブプロジェクトのディレクトリで実行すると、サブプロジェクトのみタスク実行できる
```

## 色々なタスク

ビルド系
```shell
# すべてのチェック及び出力を実行
gradle build

# アプリケーションの実行
gradle run

# 全てのチェック実行
gradle check

# 出力の削除
gradle clean
```

レポート系
```shell
# プロジェクトの一覧表示
gradle projects

# タスクの一覧表示
gradle tasks
## タスクグループに含まれていないやつも出す場合
gradle tasks --all

# タスクの詳細表示
gradle help --task ${taskName}

# タスクの依存関係表示
gradle ${taskName} --scan
```

依存関係
```shell
# 依存関係
gradle dependencies

# ビルドスクリプトの依存関係
gradle buildEnvironment

# ビルドスクリプトのプロパティ表示
gradle properties
```

オプション等
https://docs.gradle.org/current/userguide/command_line_interface.html#sec:command_line_completion

## ビルド環境

### カスタム時の優先順位

以下の優先順で先勝ちで適応される

- コマンドラインフラグ
- ルートプロジェクトの`gradle.properties`に記述してあるプロパティ
- 各プロジェクトの`gradle.properties`に記述してあるプロパティ
- 環境変数

### Gradleプロパティ

以下の優先順で先勝ちで設定される

- -Dオプションを利用(-D${key=${value}})
- GRADL_USER_HOME内のgradle.properties
- プロジェクトのディレクトリのgradle.properties
- Gradleのインストールディレクトリのgradle.properties

プロパティは``key=value``形式で記述され、以下のように取得できる

build.gradleで取得する場合
```shell
providers.gradleProperty('${key}').get()
${key}
project.${key}
project['${key}']
```

settings.gradleで取得する場合
```shell
providers.gradleProperty('${key}').get()
${key}
settings.${key}
settings['${key}']
```

keyがhoge.fugaのように.で連結されている場合は以下のように取得する必要がある

```shell
getProperty('hoge.fuga')
```

### システムプロパティ

-Dオプション、もしくはgradle.propertiesにおいて`systemProp`をprefixにつけて設定することができる

```shell
systemProp.gradle.wrapperUser=foobar
```

### 環境変数

以下の方式で環境変数を参照できる
```shell
System.getenv('${key}')
providers.environmentVariable('${key}').get()
```