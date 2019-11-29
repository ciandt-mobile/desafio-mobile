source 'https://github.com/CocoaPods/Specs.git'

platform :ios, '11.0'
use_frameworks!
inhibit_all_warnings!

workspace 'YouMovie.xcworkspace'
project 'YouMovie.xcodeproj'

# For All Targets
def all_targets
  pod 'ObjectMapper', '~> 3.4'
end

# Project Pods
def project_pods
    pod 'Alamofire', '~> 5.0.0-rc.1'
    pod 'BetterSegmentedControl', '~> 1.2'
    pod 'XCDYouTubeKit', '~> 2.8'
    pod 'Kingfisher', '~> 5.0'
end

# Test Pods
def test_pods
  pod 'Quick'
  pod 'Nimble'
  pod 'OHHTTPStubs/Swift'
end

# YouMovie Target
target 'YouMovie' do
  all_targets
  project_pods      
end

# YouMovieTests Target
target 'YouMovieTests' do
  all_targets
  test_pods
end

# inhibit_all_warnings!
post_install do |installer|
  installer.pods_project.targets.each do |target|
      target.build_configurations.each do |config|
          config.build_settings['GCC_WARN_INHIBIT_ALL_WARNINGS'] = "YES"
      end
  end
end
