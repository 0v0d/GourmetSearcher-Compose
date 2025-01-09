[English README is here](README-en.md)
## アプリ名

**GourmetSearcher**

## アプリ仕様

現在位置付近のレストランをキーワードで検索できるAndroidアプリのCompose版です。

<img src="app.gif" width="320" alt="App demo video">

## 画面概要

- **キーワード入力画面**  
  ユーザーが検索したいキーワードを入力し、検索範囲（半径）を選択する画面です。条件を入力したら位置情報検索画面に進むことができます。  

- **位置情報検索画面**  
  現在地のGPS情報を取得する画面です。位置情報が正しく取得されると、周辺の飲食店が一覧表示される店舗一覧画面に進みます。  

- **レストラン検索結果画面**  
  検索結果の飲食店がリストで表示される画面です。気になるお店を選ぶと、さらに詳しい情報を確認できる店舗詳細画面に進むことができます。  

- **レストラン詳細画面**  
  飲食店の詳細情報を表示する画面です。店舗所在地を地図アプリで確認したい場合は「Map」ボタンをタップします。また、「Hot Pepperへ」ボタンを押すと、店舗のWebページが表示されます。  

## 機能

- **レストラン検索**  
  ホットペッパーグルメサーチAPIを活用して、現在地周辺の飲食店を検索します。  
   
- **レストラン情報取得**  
  ホットペッパーグルメサーチAPIを使用して、飲食店の詳細な情報を取得します。  

- **地図アプリ連携**  
  飲食店の所在地を地図アプリで簡単に確認できる機能です。  

- **キーワード検索**  
  気になるジャンルやお店を見つけやすくするため、検索条件としてキーワードを入力し、結果を絞り込むことができます。  


## ディレクトリ構成

以下はプロジェクトの主要なディレクトリとその説明です。

```bash
.github/            # GitHubActionsとDependabotを管理
app/                # アプリケーションのエントリポイント
core/               # 共通ロジック(API クライアント,キャッシュなど)を集約
docs/               # ドキュメントを管理
feature-keyword/    # キーワード検索機能に関するコードを格納
feature-location/   # 現在地検索機能に関連するコードを管理
feature-restaurant/ # レストラン検索リストや詳細機能のコードを集約
shared-ui/          # 再利用可能な UI コンポーネント群
scripts/            # 静的解析などのスクリプトを配置
gradle/             # Gradle の依存ライブラリやバージョン管理ファイル 
```

## 各モジュールの詳細

### app/
アプリのエントリポイントを含みます。  
`MainActivity.kt` でアプリケーションを起動し、`NavigationGraph.kt` による画面遷移を管理します。

### core/
- **NetworkModule**: API 通信のための設定を提供します。
- **CacheManager**: データのキャッシュ管理。
- **PreferencesManager**: ローカルの設定管理。

### feature-keyword/
キーワード検索に関する UI、ロジック、およびリポジトリを提供します。

- `InputKeyWordScreen.kt`: キーワード検索画面。
- `UseCases`: ユーザーの検索履歴を管理します。

### feature-location/
現在地情報を取得し、近隣のレストランを検索します。

- `GetCurrentLocationUseCase.kt`: 現在地を取得するユースケース。
- `PermissionEffects.kt`: 位置情報権限を管理します。

### feature-restaurant/
レストランリストや詳細情報の表示を管理します。

- `RestaurantListViewModel.kt`: レストランリストのロジック。
- `RestaurantDetailContent.kt`: 詳細情報画面のコンポーネント。

### shared-ui/
アプリ全体で再利用可能な UI コンポーネントを含みます。

- **CustomOutlinedButton**: カスタムボタン。
- **Dialog**: 標準ダイアログ。
- **ImageCard**: レストラン画像のカード表示。

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

- コンパイルSDKバージョン: 35
- 最小SDKバージョン: 32
- ターゲットSDKバージョン: 35
- JVM バージョン: 17
- Gradle：8.10.2
- Gradle Plugin 8.7.3

### 開発言語

- Kotlin 2.0.21

### 実機端末環境

- Redmi 12 (Android 14)