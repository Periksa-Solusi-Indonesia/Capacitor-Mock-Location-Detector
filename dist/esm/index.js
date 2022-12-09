import { registerPlugin } from '@capacitor/core';
const MockDetector = registerPlugin('MockDetector', {
    web: () => import('./web').then(m => new m.MockDetectorWeb()),
});
export * from './definitions';
export { MockDetector };
//# sourceMappingURL=index.js.map