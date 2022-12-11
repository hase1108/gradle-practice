# git hooks

## git hooksとは

https://git-scm.com/book/ja/v2/Git-%E3%81%AE%E3%82%AB%E3%82%B9%E3%82%BF%E3%83%9E%E3%82%A4%E3%82%BA-Git-%E3%83%95%E3%83%83%E3%82%AF

git commitやgit pushの際に任意のスクリプトを実行することができるようになる。

### 種類

hookのタイミングによって種類がある

- クライアントサイドフック → コミットやマージなどのクライアントでの操作で実施
  - コミットワークフローフック → コミットプロセスに関するもの
    - pre-commit -> コミットメッセージが入力される前に実行される
    - prepare-commit-msg -> コミットメッセージエディタが起動する直前
    - commit-msg -> コミットメッセージを保存後に実行
    - post-commit -> コミットプロセスが全て実行された後
  - Eメールワークフロースクリプト -> git amコマンドに関連して実施、今回は割愛
  - その他
    - pre-rebase
    - post-rewrite
    - post-checkout
    - post-merge
    - pre-push
    - pre-auto-gc
- サーバサイドフック → pushされたコミットの受け取りなどのネットワーク操作で実施(updateファイルに纏める)
  - pre-receive -> クライアント側のpush処理をサーバ側が処理する時に最初に実行される
  - update -> pre-receiveと似ているが、プッシュしてきた人が更新しようとしているブランチごとに実行される
  - post-receive -> push処理が終了した後で実行される

### 仕組み

各.gitディレクトリのhooks配下(`.git/hooks`)に適切な命名をされたスクリプト(シェル含めPerlやPythonも可)を各イベントが発火したタイミングで実行可能になる。
通常.gitディレクトリはgithubなどで管理不可能であるが、以下のように`core.hooksPath`に設定をすることにより、任意の場所のスクリプトをイベント発火時に呼び出すことが出来る。

```shell
git config --local core.hooksPath ${path}

# 実行権限が必要なため
chmod -R +x ${path}/
```

pathは以下の3種類の形式で指定することができる
- 絶対パス (e.g. /home/hoge/hooks)
- ~ から辿れるパス (e.g. ~/hoge)
- リポジトリルートからの相対パス (e.g. hooks が指定してあったら ${リポジトリルート}/hooks)
