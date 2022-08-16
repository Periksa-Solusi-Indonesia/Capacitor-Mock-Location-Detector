export interface MockDetectorPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
  detectMock(): Promise<any>;
}
