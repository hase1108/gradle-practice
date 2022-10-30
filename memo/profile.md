# profile

参考: https://spring.pleiades.io/spring-boot/docs/current/reference/html/features.html#features.external-config.files

## boot 2.4からの変更点

参考: https://spring.pleiades.io/spring-boot/docs/current/reference/html/features.html#features.profiles
参考: https://spring.io/blog/2020/08/14/config-file-processing-in-spring-boot-2-4
参考: https://qiita.com/kakasak/items/3ef2aec516cb4e612fba

- プロパティファイルが複数ロードされている場合は、後勝ちで設定が上書きされる
- "#---"で区切ることで同一ファイルで複数のプロファイルを同一のファイルにまとめることができる
    - 同一のkeyが同一ファイルの複数の場所に設定されていた場合は一番下に設定されている値が有効にされる
- "spring.profiles"プロパティでプロパティファイルがどのプロファイルで有効になるか設定するのは非推奨となる
    - "spring.config.activate.on-profile"を利用する
- プロファイルに関する設定はapplication.yaml/propertiesのみに設定
- "spring.profiles.group"でプロファイルをグルーピングして、一括で設定することが可能になる
    - groupingしたさいに、同じ項目がそれぞれのグループで定義されていると、一番最後にグループに追加されているものが有効になる
- "spring.config.import"で色々なところから設定をimport可能