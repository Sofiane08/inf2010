package CircularDeps;

import java.util.HashSet;
import java.util.Iterator;

public class CodeBase {
    private SourceFile[] sourceFiles;

    public CodeBase(SourceFile[] sourceFiles)
    {
        this.sourceFiles = sourceFiles;
    }

    public boolean hasCircularDependencies()
    {
        for (SourceFile file : this.sourceFiles) {
            HashSet<SourceFile> dependentFiles = new HashSet<>();
            if (existsCircularDependencies(file, dependentFiles)) {
                return true;
            }
        }
        return false;
    }

    // DFS partant de <<file>> détectant s'il existe
    // des dépendances circulaires dans les fichiers source.
    private boolean existsCircularDependencies(SourceFile file, HashSet<SourceFile> dependentFiles)
    {
        // À compléter
    	if(dependentFiles.contains(file))
    		return true;
    	dependentFiles.add(file);
    	
    	for(Iterator<SourceFile> i = file.getDependencies().iterator(); i.hasNext() ;) {
    		if (this.existsCircularDependencies(i.next(), dependentFiles)) return true;
    	}
    	
    	return false;
    }
}
