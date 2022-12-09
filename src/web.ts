import { WebPlugin } from '@capacitor/core';

import type { MockDetectorPlugin } from './definitions';

export class MockDetectorWeb extends WebPlugin implements MockDetectorPlugin {
  detectMock(): Promise<any> {
    throw false;
  }
}
