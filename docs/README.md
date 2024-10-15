[English README is here](README-en.md)
## アプリ名

GourmetSearcher

## アプリ仕様

現在位置付近のレストランをキーワードで検索できるAndroidアプリのCompose版です。

<img src="app.gif" width="320" alt="アプリのデモ動画">

### APIKeyについて

本アプリは[ホットペッパーのグルメサーチAPI](https://webservice.recruit.co.jp/doc/hotpepper/reference.html)
を使用しています。
ご利用の際には[こちら](https://webservice.recruit.co.jp/register/)にメールアドレスを登録していただく必要がございます。
登録していただくと、メールにてAPIKeyが送信されますので、そちらを以下のようにしていただくことで、ご利用可能になります。

- APIKeyはプロジェクトのルートディレクトリにある local.properties ファイルに

```properties
API_KEY="YOUR_API_KEY"
```

の形式で、APIキーを設定して下さい。

### 不具合及び使用上の注意

- 仮想端末を使用する際に、GPSが取得できないという不具合があります。</br>
  (実機端末では、不具合なく動作します。)

### 動作対象端末・OS

#### 動作対象OS

Android 14

## 開発環境

Android Studio Koala Feature Drop 2024.1.2 Canary 7

- コンパイルSDKバージョン: 34
- 最小SDKバージョン: 32
- ターゲットSDKバージョン: 34
- Java：VERSION_17
- Gradle：8.8
- Gradle Plugin 8.5.0
- minSdk：32
- targetSdk：34
- kotlinCompilerExtensionVersion 1.5.3

### 開発言語

- Kotlin 2.0.0

### 実機端末環境

- Redmi 12 (Android 14)

## アプリケーション機能

### 機能一覧

- レストラン検索：ホットペッパーグルメサーチAPIを使用して、現在地周辺の飲食店を検索する。
- レストラン情報取得：ホットペッパーグルメサーチAPIを使用して、飲食店の詳細情報を取得する。
- 地図アプリ連携：飲食店の所在地を地図アプリに表示する。
- キーワード検索：キーワードを入力することで、ホットペッパーグルメサーチAPIの検索結果を絞る。

### 画面概要

- キーワード入力画面 (InputSearchTermsFragment) ： キーワードを入力し、半径が選択されると位置情報検索画面へ遷移する。
- 位置情報検索画面 (LocationSearchFragment) : GPSの取得に成功すると店舗一覧画面へ遷移する。
- レストラン検索結果画面 (RestaurantListViewFragment) : 検索結果の飲食店を一覧表示し、選択されると店舗詳細画面へ遷移する。
- レストラン詳細画面 (RestaurantDetailFragment) : 店舗の詳細を表示し、Mapボタンを押された時にはマップアプリへ遷移
  する。「Hot Pepperへ」ボタンを押されたときにWebページを表示する。

## 使用しているAPI, SDK, ライブラリ

### Android
- Accompanist Permissions
- AndroidX Core KTX
- AndroidX DataStore Preferences
- AndroidX Lifecycle Runtime KTX
- AndroidX Activity Compose
- AndroidX Compose BOM
- AndroidX Compose UI
- AndroidX Compose UI Graphics
- AndroidX Compose UI Test Manifest
- AndroidX Compose UI Tooling
- AndroidX Compose UI Tooling Preview
- AndroidX Material3
- AndroidX Navigation UI KTX
- AndroidX Navigation Runtime KTX
- AndroidX Navigation Compose
- AndroidX Hilt Navigation Compose
- Play Services Location

### 画像ライブラリ
- Coil Compose

### 静的解析
- Detekt Rules Twitter
- Detekt Formatting
- Detekt Rules

### コレクション
- Kotlinx Collections Immutable

### シリアライズ
- Kotlinx Serialization JSON

### 依存性注入
- Dagger Hilt Android Compiler
- Dagger Hilt Android

### ネットワーキング
- Retrofit
- Retrofit Converter Moshi
- Moshi Kotlin

### デバッグツール
- LeakCanary

### ユニットテスト
- JUnit
- Dagger Hilt Android Testing
- Mockito Core
- AndroidX Runner
- Kotlinx Coroutines Test
- AndroidX Core Testing

### Androidテスト
- AndroidX JUnit
- AndroidX Espresso Core
- AndroidX UI Automator
- AndroidX UI Test JUnit4 Android

### その他
- AndroidX Compose Material Icons Extended
- AndroidX Lifecycle Runtime Compose Android

### プラグイン
- Android Application
- JetBrains Kotlin Android
- Kotlin Kapt
- Dagger Hilt Android
- Kotlin Parcelize
- Secrets Gradle Plugin
- Detekt
- Serialization
- Compose Compiler
