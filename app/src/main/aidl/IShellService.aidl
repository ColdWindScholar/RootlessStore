interface IShellService {
    void exec(String pluginExecuteEntryPoint, String pluginPackageDirectory, IShellCallback callback);
    boolean kill(int progressPid);
}