import { registerPlugin } from '@capacitor/core';

import type { MockDetectorPlugin } from './definitions';

const MockDetector = registerPlugin<MockDetectorPlugin>('MockDetector', {
  web: () => import('./web').then(m => new m.MockDetectorWeb()),
});

export * from './definitions';
export { MockDetector };
