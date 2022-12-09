import { WebPlugin } from '@capacitor/core';
import type { MockDetectorPlugin } from './definitions';
export declare class MockDetectorWeb extends WebPlugin implements MockDetectorPlugin {
    detectMock(): Promise<any>;
}
