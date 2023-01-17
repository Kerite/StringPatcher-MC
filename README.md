# Universal Language Patch (ULP)

A Minecraft mod patching texts that mod hardcoded

## 中文说明

在 `<游戏目录>/translations` 文件夹中创建 .json 文件，内容为`"替换前的文本": "替换后的文本"`（下面的三种都支持），可以有多个文件

```json
{
  "Demo text": "实例文本",
  "translation.key.demo": "Demo text",
  "Plain demo text": "§ePlain demo text"
}
```

使用 `/ulp reload` 重新加载，可能需要等待几秒钟生效

## English

Some mod uses hardcoded text that can't be translated without modifying source code.

This mod use Mixin to fix this problem

### Usage

This mod currently only supports 1.16.5 forge

After installing, put jsons into `<game_folder>/translations` with content like following:

```json
{
  "Demo text": "实例文本",
  "translation.key.demo": "Demo text",
  "Plain demo text": "§ePlain demo text"
}
```

### Commands

`/ulp reload` : Reload translation files(.json files created above), need to wait a few seconds

### TODO

- Multi-language support
