var capacitorMockDetector = (function (exports, core) {
    'use strict';

    const MockDetector = core.registerPlugin('MockDetector', {
        web: () => Promise.resolve().then(function () { return web; }).then(m => new m.MockDetectorWeb()),
    });

    class MockDetectorWeb extends core.WebPlugin {
        detectMock() {
            const data = {
                value: false,
                message: "web not implemented",
            };
            throw JSON.parse(JSON.stringify(data));
        }
    }

    var web = /*#__PURE__*/Object.freeze({
        __proto__: null,
        MockDetectorWeb: MockDetectorWeb
    });

    exports.MockDetector = MockDetector;

    Object.defineProperty(exports, '__esModule', { value: true });

    return exports;

})({}, capacitorExports);
//# sourceMappingURL=plugin.js.map
