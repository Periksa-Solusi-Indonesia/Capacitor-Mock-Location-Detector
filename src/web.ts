import { WebPlugin } from '@capacitor/core';

import type { MockDetectorPlugin } from './definitions';

export class MockDetectorWeb extends WebPlugin implements MockDetectorPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
