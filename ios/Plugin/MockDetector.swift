import Foundation

@objc public class MockDetector: NSObject {
    @objc public func echo(_ value: String) -> String {
        print(value)
        return value
    }
}
