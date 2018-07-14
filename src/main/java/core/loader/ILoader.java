package core.loader;

import core.exception.D2pNotFoundException;

public interface ILoader {

    public void load(String projectPath) throws D2pNotFoundException;
}
