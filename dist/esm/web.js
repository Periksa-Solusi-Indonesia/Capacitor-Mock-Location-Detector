import { WebPlugin } from '@capacitor/core';
export class MockDetectorWeb extends WebPlugin {
    detectMock() {
        throw false;
    }
}
//# sourceMappingURL=web.js.map