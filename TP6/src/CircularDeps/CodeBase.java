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

    // DFS partant de <<file>> dÃ©tectant s'il existe
    // des dÃ©pendances circulaires dans les fichiers source.
    private boolean existsCircularDependencies(SourceFile file, HashSet<SourceFile> dependentFiles)
    {
        // Ã€ complÃ©ter
    	//si ce fichier est déjà vue, retourner vrai
    	if(dependentFiles.contains(file))
    		return true;
    	//sinon, l'ajouter aux fichiers vus
    	dependentFiles.add(file);
    	
    	//appliquer la méthode à chaque enfants.
    	for(Iterator<SourceFile> i = file.getDependencies().iterator(); i.hasNext() ;) {
    		if (this.existsCircularDependencies(i.next(), dependentFiles)) return true;
    	}
    	//si nous nous rendons ici, aucun enfant n'as retourné vrai, donc aucune circular dependencies.
    	return false;
    }
}
