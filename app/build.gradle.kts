plugins {
	alias(libs.plugins.android.application)
	alias(libs.plugins.jetbrains.kotlin.android)
	alias(libs.plugins.kotlin.serialization)
	alias(libs.plugins.dagger.hilt)
	kotlin("kapt")
}

android {
	namespace = "com.davrukin.fruitsandvegetables"
	compileSdk = 34

	defaultConfig {
		applicationId = "com.davrukin.fruitsandvegetables"
		minSdk = 26
		targetSdk = 34
		versionCode = 1
		versionName = "1.0"

		testInstrumentationRunner = "com.davrukin.fruitsandvegetables.HiltTestRunner"
		vectorDrawables {
			useSupportLibrary = true
		}
	}

	buildTypes {
		release {
			isMinifyEnabled = false
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"),
				"proguard-rules.pro"
			)
		}
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_1_8
		targetCompatibility = JavaVersion.VERSION_1_8
	}
	kotlinOptions {
		jvmTarget = "1.8"
	}
	buildFeatures {
		compose = true
	}
	composeOptions {
		kotlinCompilerExtensionVersion = "1.5.3"
	}
	packaging {
		resources {
			excludes.addAll(
				listOf(
					"/META-INF/{AL2.0,LGPL2.1}",
					"META-INF/LICENSE.md",
					"META-INF/LICENSE-notice.md"
				)
			)
		}
	}
	kapt {
		correctErrorTypes = true
	}
}

dependencies {

	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.lifecycle.runtime.ktx)
	implementation(libs.androidx.lifecycle.runtime.ktx.compose)
	implementation(libs.androidx.activity.compose)
	implementation(platform(libs.androidx.compose.bom))
	implementation(libs.androidx.ui)
	implementation(libs.androidx.ui.graphics)
	implementation(libs.androidx.ui.tooling.preview)
	implementation(libs.androidx.material3)

	implementation(libs.paging.runtime)
	implementation(libs.paging.compose)
	implementation(libs.ktor.core)
	implementation(libs.ktor.cio)
	implementation(libs.kotlin.coroutines)
	implementation(libs.kotlin.serialization.json)
	implementation(libs.retrofit)
	implementation(libs.retrofit.kotlinx.serialization)
	implementation(platform(libs.okhttp.bom))
	implementation(libs.okhttp)
	implementation(libs.okhttp.interceptor)
	implementation(libs.hilt)
	kapt(libs.hilt.compiler)
	kaptTest(libs.hilt.compiler)
	implementation(libs.hilt.navigation)
	implementation(libs.androidx.material)

	testImplementation(libs.junit)
	testImplementation(libs.mockk.base)
	testImplementation(libs.kotlin.test)

	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
	androidTestImplementation(platform(libs.androidx.compose.bom))
	androidTestImplementation(libs.androidx.ui.test.junit4)
	androidTestImplementation(libs.mockk.android)
	androidTestImplementation(libs.mockk.agent)
	androidTestImplementation(libs.kotlin.test)
	androidTestImplementation(libs.hilt.test)
	kaptAndroidTest(libs.hilt.compiler)

	debugImplementation(libs.androidx.ui.tooling)
	debugImplementation(libs.androidx.ui.test.manifest)
}