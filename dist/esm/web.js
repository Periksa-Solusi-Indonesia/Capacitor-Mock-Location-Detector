import { WebPlugin } from '@capacitor/core';
export class MockDetectorWeb extends WebPlugin {
    detectMock() {
        const data = {
            value: false,
            message: "web not implemented",
        };
        throw JSON.parse(JSON.stringify(data));
    }
}
//# sourceMappingURL=web.js.map