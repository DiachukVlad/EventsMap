Pod::Spec.new do |spec|
    spec.name                     = 'sharedClient'
    spec.version                  = '1.0-SNAPSHOT'
    spec.homepage                 = 'https://github.com/Kotlin/multitarget-xcode-with-kotlin-cocoapods-sample'
    spec.source                   = { :http=> ''}
    spec.authors                  = ''
    spec.license                  = ''
    spec.summary                  = 'Kotlin CocoaPods library'
    spec.vendored_frameworks      = 'build/cocoapods/framework/sharedClient.framework'
    spec.libraries                = 'c++'
    spec.ios.deployment_target = '13.5'
                
                
    spec.pod_target_xcconfig = {
        'KOTLIN_PROJECT_PATH' => ':sharedClient',
        'PRODUCT_MODULE_NAME' => 'sharedClient',
    }
                
    spec.script_phases = [
        {
            :name => 'Build sharedClient',
            :execution_position => :before_compile,
            :shell_path => '/bin/sh',
            :script => <<-SCRIPT
                if [ "YES" = "$COCOAPODS_SKIP_KOTLIN_BUILD" ]; then
                  echo "Skipping Gradle build task invocation due to COCOAPODS_SKIP_KOTLIN_BUILD environment variable set to \"YES\""
                  exit 0
                fi
                set -ev
                REPO_ROOT="$PODS_TARGET_SRCROOT"
                "$REPO_ROOT/../../../../../private/var/folders/6t/73m0bwhn02d9nyz05vzb62km0000gn/T/wrap11loc/gradlew" -p "$REPO_ROOT" $KOTLIN_PROJECT_PATH:syncFramework \
                    -Pkotlin.native.cocoapods.platform=$PLATFORM_NAME \
                    -Pkotlin.native.cocoapods.archs="$ARCHS" \
                    -Pkotlin.native.cocoapods.configuration="$CONFIGURATION"
            SCRIPT
        }
    ]
                
end