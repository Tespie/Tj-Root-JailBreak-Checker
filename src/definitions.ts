export interface RootCheckerPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
