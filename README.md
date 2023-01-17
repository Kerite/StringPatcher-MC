# Universal Language Patch (ULP)

A Minecraft mod patching texts that mod not translated

Some mod uses `StringTextComponent` than `TranslationTextComponent` that can't be translated without changing source
code.

This mod use Mixin to fix this problem

## Usage

This mod currently only supports 1.16.5 forge

After installing, put jsons into `<game_folder>/translations` with content like following:

```json
{
  "Demo text": "实例文本",
  "translation.key.demo": "Demo text",
  "Plain demo text": "§ePlain demo text"
}
```

## Commands

`/ulp reload` :Reload translation files(.json files created above)
