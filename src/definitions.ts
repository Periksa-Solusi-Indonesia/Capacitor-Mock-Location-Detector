export interface MockDetectorPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
